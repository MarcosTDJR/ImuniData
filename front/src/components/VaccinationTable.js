import React from 'react';
import '../styles/table.css';

const VaccinationTable = ({ data, loading, onEdit, onDelete }) => {
  if (loading) {
    return (
      <div className="loading">
        <div className="spinner"></div>
        <p>Carregando dados...</p>
      </div>
    );
  }

  if (!data || data.length === 0) {
    return (
      <div className="no-data">
        <p>Nenhum registro de vacinação encontrado.</p>
        <small>Clique em "Nova Vacinação" para adicionar um registro.</small>
      </div>
    );
  }

  const handleDelete = (id) => {
    if (window.confirm('Tem certeza que deseja deletar este registro?')) {
      onDelete(id);
    }
  };

  return (
    <div className="table-container">
      <div className="table-wrapper">
        <table>
          <thead>
            <tr>
              <th>ID</th>
              <th>Município</th>
              <th>Estado</th>
              <th>Vacina</th>
              <th>Dose</th>
              <th>Idade</th>
              <th>Data Registro</th>
              <th>Ações</th>
            </tr>
          </thead>
          <tbody>
            {data.map((registro) => {
              const dataRegistro = registro.data_registro || registro.dataRegistro;
              const idade = registro.idade_paciente ?? registro.idadePaciente;

              return (
                <tr key={registro.id}>
                  <td>#{registro.id}</td>
                  <td>{registro.municipio}</td>
                  <td>{registro.estado}</td>
                  <td>
                    <span className="vaccine-badge">{registro.vacina}</span>
                  </td>
                  <td>{registro.dose}</td>
                  <td>{idade ?? '-'}</td>
                  <td>{dataRegistro ? new Date(dataRegistro).toLocaleDateString('pt-BR') : '-'}</td>
                  <td>
                  <div className="action-buttons">
                    <button
                      className="btn-sm btn-edit"
                      onClick={() => onEdit(registro)}
                      title="Editar registro"
                    >
                      ✎ Editar
                    </button>
                    <button
                      className="btn-sm btn-delete"
                      onClick={() => handleDelete(registro.id)}
                      title="Deletar registro"
                    >
                      🗑 Deletar
                    </button>
                  </div>
                  </td>
                </tr>
              );
            })}
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default VaccinationTable;
