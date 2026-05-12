import React, { useState, useEffect } from 'react';
import VaccinationTable from './VaccinationTable';
import VaccinationForm from './VaccinationForm';
import Filters from './Filters';
import vacinacaoService from '../services/vacinacaoService';
import '../styles/dashboard.css';

const PAGE_SIZE = 5000;

const Dashboard = () => {
  const [registros, setRegistros] = useState([]);
  const [filteredRegistros, setFilteredRegistros] = useState([]);
  const [loading, setLoading] = useState(true);
  const [loadingMore, setLoadingMore] = useState(false);
  const [activeTab, setActiveTab] = useState('listing');
  const [selectedRegistro, setSelectedRegistro] = useState(null);
  const [currentFilters, setCurrentFilters] = useState({});
  const [page, setPage] = useState(0);
  const [hasMore, setHasMore] = useState(true);
  const [stats, setStats] = useState({
    totalRegistros: 0,
    totalVacinas: 0,
    totalEstados: 0,
  });
  const [message, setMessage] = useState(null);

  const atualizarStats = (dados) => {
    const totalVacinas = new Set(dados.map((r) => r.vacina)).size;
    const totalEstados = new Set(dados.map((r) => r.estado)).size;
    setStats({
      totalRegistros: dados.length,
      totalVacinas,
      totalEstados,
    });
  };

  // Carrega os dados iniciais
  useEffect(() => {
    const carregarDadosIniciais = async () => {
      setLoading(true);
      try {
        const dados = await vacinacaoService.listar({ page: 0, size: PAGE_SIZE });
        setRegistros(dados);
        setFilteredRegistros(dados);
        setPage(0);
        setHasMore(dados.length === PAGE_SIZE);

        const totalVacinas = new Set(dados.map((r) => r.vacina)).size;
        const totalEstados = new Set(dados.map((r) => r.estado)).size;
        setStats({
          totalRegistros: dados.length,
          totalVacinas,
          totalEstados,
        });
      } catch (error) {
        console.error('Erro ao carregar dados:', error);
        setMessage({
          texto: 'Erro ao carregar dados. Verifique se o backend está em execução.',
          tipo: 'error',
        });
      } finally {
        setLoading(false);
      }
    };

    carregarDadosIniciais();
  }, []);

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

  const filtrosAtivos = Boolean(currentFilters.vacina || currentFilters.estado || currentFilters.municipio);

  const handleLoadMore = async () => {
    if (loadingMore || !hasMore || filtrosAtivos) return;

    setLoadingMore(true);
    try {
      const proximaPagina = page + 1;
      const novosDados = await vacinacaoService.listar({ page: proximaPagina, size: PAGE_SIZE });
      const dadosAtualizados = [...registros, ...novosDados];

      setRegistros(dadosAtualizados);
      setFilteredRegistros(dadosAtualizados);
      setPage(proximaPagina);
      setHasMore(novosDados.length === PAGE_SIZE);
      atualizarStats(dadosAtualizados);
    } catch (error) {
      console.error('Erro ao carregar mais dados:', error);
      mostrarMensagem('Erro ao carregar mais dados.', 'error');
    } finally {
      setLoadingMore(false);
    }
  };

  const handleEdit = (registro) => {
    setSelectedRegistro(registro);
    setActiveTab('form');
    window.scrollTo(0, 0);
  };

  const handleDelete = async (id) => {
    try {
      await vacinacaoService.deletar(id);
      const registrosAtualizados = registros.filter((r) => r.id !== id);
      const filtradosAtualizados = filteredRegistros.filter((r) => r.id !== id);

      setRegistros(registrosAtualizados);
      setFilteredRegistros(filtradosAtualizados);
      atualizarStats(registrosAtualizados);
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
        const registrosAtualizados = registros.map((r) =>
          (r.id === selectedRegistro.id ? registroAtualizado : r)
        );
        const filtradosAtualizados = filteredRegistros.map((r) =>
          (r.id === selectedRegistro.id ? registroAtualizado : r)
        );

        setRegistros(registrosAtualizados);
        setFilteredRegistros(filtradosAtualizados);
        atualizarStats(registrosAtualizados);
        mostrarMensagem('Registro atualizado com sucesso!', 'success');
      } else {
        // Criar
        const novoRegistro = await vacinacaoService.criar(formData);
        const registrosAtualizados = [...registros, novoRegistro];
        const filtradosAtualizados = [...filteredRegistros, novoRegistro];

        setRegistros(registrosAtualizados);
        setFilteredRegistros(filtradosAtualizados);
        atualizarStats(registrosAtualizados);
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

              <div className="load-more-container">
                {!filtrosAtivos && hasMore && (
                  <button
                    className="btn-load-more"
                    onClick={handleLoadMore}
                    disabled={loadingMore}
                  >
                    {loadingMore ? 'Carregando...' : 'Carregar mais 5.000 registros'}
                  </button>
                )}
                {filtrosAtivos && (
                  <small className="load-more-note">
                    Para carregar mais dados, limpe os filtros ativos.
                  </small>
                )}
              </div>
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
