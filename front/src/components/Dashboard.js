import React, { useState, useEffect } from 'react';
import VaccinationTable from './VaccinationTable';
import VaccinationForm from './VaccinationForm';
import Filters from './Filters';
import vacinacaoService from '../services/vacinacaoService';
import '../styles/dashboard.css';

const Dashboard = () => {
  const [registros, setRegistros] = useState([]);
  const [filteredRegistros, setFilteredRegistros] = useState([]);
  const [loading, setLoading] = useState(true);
  const [activeTab, setActiveTab] = useState('listing');
  const [selectedRegistro, setSelectedRegistro] = useState(null);
  const [currentFilters, setCurrentFilters] = useState({});
  const [stats, setStats] = useState({
    totalRegistros: 0,
    totalVacinas: 0,
    totalEstados: 0,
  });
  const [message, setMessage] = useState(null);

  // Carrega os dados iniciais
  useEffect(() => {
    carregarDados();
  }, []);

  const carregarDados = async () => {
    setLoading(true);
    try {
      const dados = await vacinacaoService.listar();
      setRegistros(dados);
      setFilteredRegistros(dados);

      // Calcula estatísticas
      const totalVacinas = new Set(dados.map((r) => r.vacina)).size;
      const totalEstados = new Set(dados.map((r) => r.estado)).size;
      setStats({
        totalRegistros: dados.length,
        totalVacinas,
        totalEstados,
      });
    } catch (error) {
      console.error('Erro ao carregar dados:', error);
      mostrarMensagem('Erro ao carregar dados. Verifique se o backend está em execução.', 'error');
    } finally {
      setLoading(false);
    }
  };

  const mostrarMensagem = (texto, tipo = 'info') => {
    setMessage({ texto, tipo });
    setTimeout(() => setMessage(null), 3000);
  };

  const handleFilter = async (filters) => {
    setCurrentFilters(filters);
    setLoading(true);

    try {
      let dados = registros;

      if (filters.vacina) {
        const dadosVacina = await vacinacaoService.buscarPorVacina(filters.vacina);
        dados = dadosVacina;
      }

      if (filters.estado) {
        const dadosEstado = dados.length === registros.length
          ? await vacinacaoService.buscarPorEstado(filters.estado)
          : dados.filter((r) => r.estado === filters.estado);
        dados = dadosEstado;
      }

      if (filters.municipio) {
        dados = dados.filter((r) =>
          r.municipio.toLowerCase().includes(filters.municipio.toLowerCase())
        );
      }

      setFilteredRegistros(dados);
    } catch (error) {
      console.error('Erro ao filtrar dados:', error);
      mostrarMensagem('Erro ao aplicar filtros', 'error');
    } finally {
      setLoading(false);
    }
  };

  const handleResetFilters = () => {
    setCurrentFilters({});
    setFilteredRegistros(registros);
  };

  const handleEdit = (registro) => {
    setSelectedRegistro(registro);
    setActiveTab('form');
    window.scrollTo(0, 0);
  };

  const handleDelete = async (id) => {
    try {
      await vacinacaoService.deletar(id);
      setRegistros(registros.filter((r) => r.id !== id));
      setFilteredRegistros(filteredRegistros.filter((r) => r.id !== id));
      mostrarMensagem('Registro deletado com sucesso!', 'success');
    } catch (error) {
      console.error('Erro ao deletar registro:', error);
      mostrarMensagem('Erro ao deletar registro', 'error');
    }
  };

  const handleFormSubmit = async (formData) => {
    try {
      if (selectedRegistro) {
        // Atualizar
        const registroAtualizado = await vacinacaoService.atualizar(selectedRegistro.id, formData);
        setRegistros(registros.map((r) => (r.id === selectedRegistro.id ? registroAtualizado : r)));
        setFilteredRegistros(
          filteredRegistros.map((r) => (r.id === selectedRegistro.id ? registroAtualizado : r))
        );
        mostrarMensagem('Registro atualizado com sucesso!', 'success');
      } else {
        // Criar
        const novoRegistro = await vacinacaoService.criar(formData);
        setRegistros([...registros, novoRegistro]);
        setFilteredRegistros([...filteredRegistros, novoRegistro]);
        mostrarMensagem('Registro criado com sucesso!', 'success');
      }

      setActiveTab('listing');
      setSelectedRegistro(null);
    } catch (error) {
      console.error('Erro ao salvar registro:', error);
      mostrarMensagem('Erro ao salvar registro', 'error');
    }
  };

  const handleFormCancel = () => {
    setActiveTab('listing');
    setSelectedRegistro(null);
  };

  return (
    <div className="dashboard">
      {message && (
        <div className={`alert alert-${message.tipo}`}>
          {message.texto}
        </div>
      )}

      <div className="header">
        <h1>💉 ImuniData - Sistema de Monitoramento de Vacinação</h1>
        <p>Gestão completa de registros de vacinação por estado e tipo de vacina</p>
      </div>

      <div className="container">
        {/* Stats */}
        <div className="stats-grid">
          <div className="stat-card">
            <h3>📊 Total de Registros</h3>
            <div className="value">{stats.totalRegistros}</div>
            <div className="subtitle">aplicações registradas</div>
          </div>
          <div className="stat-card">
            <h3>💉 Tipos de Vacinas</h3>
            <div className="value">{stats.totalVacinas}</div>
            <div className="subtitle">vacinas diferentes</div>
          </div>
          <div className="stat-card">
            <h3>📍 Estados Cobertos</h3>
            <div className="value">{stats.totalEstados}</div>
            <div className="subtitle">estados registrados</div>
          </div>
        </div>

        {/* Tabs */}
        <div className="tabs">
          <button
            className={`tab-button ${activeTab === 'listing' ? 'active' : ''}`}
            onClick={() => setActiveTab('listing')}
          >
            📋 Listagem
          </button>
          <button
            className={`tab-button ${activeTab === 'form' ? 'active' : ''}`}
            onClick={() => {
              setActiveTab('form');
              setSelectedRegistro(null);
            }}
          >
            ➕ Nova Vacinação
          </button>
        </div>

        {/* Tab: Listing */}
        <div className={`tab-content ${activeTab === 'listing' ? 'active' : ''}`}>
          <Filters onFilter={handleFilter} onReset={handleResetFilters} />
          <div className="main-content">
            <div className="content-padding">
              <VaccinationTable
                data={filteredRegistros}
                loading={loading}
                onEdit={handleEdit}
                onDelete={handleDelete}
              />
            </div>
          </div>
        </div>

        {/* Tab: Form */}
        <div className={`tab-content ${activeTab === 'form' ? 'active' : ''}`}>
          <VaccinationForm
            onSubmit={handleFormSubmit}
            onCancel={handleFormCancel}
            initialData={selectedRegistro}
          />
        </div>
      </div>
    </div>
  );
};

export default Dashboard;
