import React, { useState } from 'react';
import '../styles/filters.css';

const Filters = ({ onFilter, onReset }) => {
  const [filters, setFilters] = useState({
    vacina: '',
    estado: '',
    municipio: '',
  });

  const estados = ['SP', 'MG', 'RJ', 'RS', 'BA', 'PR', 'PE', 'CE', 'PA', 'SC'];
  const vacinas = ['BCG', 'Gripe', 'COVID-19', 'Poliomielite', 'Tétano', 'Meningite', 'Hepatite A', 'Hepatite B'];

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFilters((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  const handleFilter = () => {
    const activeFilters = Object.fromEntries(
      Object.entries(filters).filter(([_, value]) => value !== '')
    );
    onFilter(activeFilters);
  };

  const handleReset = () => {
    setFilters({
      vacina: '',
      estado: '',
      municipio: '',
    });
    onReset();
  };

  const hasActiveFilters = Object.values(filters).some((value) => value !== '');

  return (
    <div className="filters-container">
      <h3 style={{ marginBottom: '15px' }}>🔍 Filtros Especializados</h3>
      <div className="filters-grid">
        <div className="filter-group">
          <label htmlFor="vacina">Tipo de Vacina</label>
          <select
            id="vacina"
            name="vacina"
            value={filters.vacina}
            onChange={handleInputChange}
          >
            <option value="">-- Selecionar --</option>
            {vacinas.map((vacina) => (
              <option key={vacina} value={vacina}>
                {vacina}
              </option>
            ))}
          </select>
        </div>

        <div className="filter-group">
          <label htmlFor="estado">Estado</label>
          <select
            id="estado"
            name="estado"
            value={filters.estado}
            onChange={handleInputChange}
          >
            <option value="">-- Selecionar --</option>
            {estados.map((estado) => (
              <option key={estado} value={estado}>
                {estado}
              </option>
            ))}
          </select>
        </div>

        <div className="filter-group">
          <label htmlFor="municipio">Município</label>
          <input
            id="municipio"
            type="text"
            name="municipio"
            placeholder="Digite o município..."
            value={filters.municipio}
            onChange={handleInputChange}
          />
        </div>

        <div className="filter-buttons">
          <button className="btn-primary" onClick={handleFilter}>
            🔎 Filtrar
          </button>
          {hasActiveFilters && (
            <button className="btn-secondary" onClick={handleReset}>
              ✕ Limpar
            </button>
          )}
        </div>
      </div>

      {hasActiveFilters && (
        <div className="active-filters">
          <strong>Filtros ativos:</strong>
          {filters.vacina && (
            <span className="filter-tag">
              💉 {filters.vacina}
              <span
                className="remove"
                onClick={() => setFilters((prev) => ({ ...prev, vacina: '' }))}
              >
                ✕
              </span>
            </span>
          )}
          {filters.estado && (
            <span className="filter-tag">
              📍 {filters.estado}
              <span
                className="remove"
                onClick={() => setFilters((prev) => ({ ...prev, estado: '' }))}
              >
                ✕
              </span>
            </span>
          )}
          {filters.municipio && (
            <span className="filter-tag">
              🏙️ {filters.municipio}
              <span
                className="remove"
                onClick={() => setFilters((prev) => ({ ...prev, municipio: '' }))}
              >
                ✕
              </span>
            </span>
          )}
        </div>
      )}
    </div>
  );
};

export default Filters;
