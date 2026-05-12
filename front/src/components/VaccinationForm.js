import React, { useState, useEffect } from 'react';
import '../styles/form.css';

const VaccinationForm = ({ onSubmit, onCancel, initialData = null }) => {
  const [formData, setFormData] = useState({
    municipio: '',
    estado: '',
    vacina: '',
    dose: '',
    quantidadeAplicada: '',
    dataRegistro: new Date().toISOString().split('T')[0],
  });

  const [errors, setErrors] = useState({});
  const [loading, setLoading] = useState(false);
  const [success, setSuccess] = useState(false);

  const estados = ['SP', 'MG', 'RJ', 'RS', 'BA', 'PR', 'PE', 'CE', 'PA', 'SC'];
  const vacinas = ['BCG', 'Gripe', 'COVID-19', 'Poliomielite', 'Tétano', 'Meningite', 'Hepatite A', 'Hepatite B'];
  const doses = ['1ª dose', '2ª dose', '3ª dose', 'Reforço', 'Dose única'];

  useEffect(() => {
    if (initialData) {
      setFormData({
        ...initialData,
        dataRegistro: initialData.dataRegistro || new Date().toISOString().split('T')[0],
      });
    }
  }, [initialData]);

  const validateForm = () => {
    const newErrors = {};

    if (!formData.municipio.trim()) {
      newErrors.municipio = 'Município é obrigatório';
    }
    if (!formData.estado) {
      newErrors.estado = 'Estado é obrigatório';
    }
    if (!formData.vacina) {
      newErrors.vacina = 'Tipo de vacina é obrigatório';
    }
    if (!formData.dose) {
      newErrors.dose = 'Dose é obrigatória';
    }
    if (!formData.quantidadeAplicada || formData.quantidadeAplicada <= 0) {
      newErrors.quantidadeAplicada = 'Quantidade deve ser maior que 0';
    }
    if (!formData.dataRegistro) {
      newErrors.dataRegistro = 'Data do registro é obrigatória';
    }

    setErrors(newErrors);
    return Object.keys(newErrors).length === 0;
  };

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({
      ...prev,
      [name]: value,
    }));
    // Clear error for this field if it exists
    if (errors[name]) {
      setErrors((prev) => ({
        ...prev,
        [name]: '',
      }));
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (!validateForm()) {
      return;
    }

    setLoading(true);
    setSuccess(false);

    try {
      await onSubmit(formData);
      setSuccess(true);
      setFormData({
        municipio: '',
        estado: '',
        vacina: '',
        dose: '',
        quantidadeAplicada: '',
        dataRegistro: new Date().toISOString().split('T')[0],
      });
      setTimeout(() => {
        setSuccess(false);
      }, 3000);
    } catch (error) {
      console.error('Erro ao enviar formulário:', error);
      setErrors({ submit: 'Erro ao enviar o formulário. Tente novamente.' });
    } finally {
      setLoading(false);
    }
  };

  return (
    <form className="form-container" onSubmit={handleSubmit}>
      <h2>{initialData ? '✎ Editar Registro' : '➕ Novo Registro de Vacinação'}</h2>

      {success && (
        <div className="form-success">
          ✓ Registro {initialData ? 'atualizado' : 'criado'} com sucesso!
        </div>
      )}

      {errors.submit && (
        <div style={{ color: '#dc3545', marginBottom: '20px' }}>
          ✗ {errors.submit}
        </div>
      )}

      <div className="form-step">
        <h3>Localização</h3>
        <div className="form-row">
          <div className="form-group">
            <label htmlFor="municipio">
              Município <span className="required">*</span>
            </label>
            <input
              id="municipio"
              type="text"
              name="municipio"
              placeholder="Ex: São Paulo"
              value={formData.municipio}
              onChange={handleInputChange}
              style={errors.municipio ? { borderColor: '#dc3545' } : {}}
            />
            {errors.municipio && <span className="form-error">{errors.municipio}</span>}
          </div>

          <div className="form-group">
            <label htmlFor="estado">
              Estado <span className="required">*</span>
            </label>
            <select
              id="estado"
              name="estado"
              value={formData.estado}
              onChange={handleInputChange}
              style={errors.estado ? { borderColor: '#dc3545' } : {}}
            >
              <option value="">-- Selecionar --</option>
              {estados.map((estado) => (
                <option key={estado} value={estado}>
                  {estado}
                </option>
              ))}
            </select>
            {errors.estado && <span className="form-error">{errors.estado}</span>}
          </div>
        </div>
      </div>

      <div className="form-step">
        <h3>Informações da Vacinação</h3>
        <div className="form-row">
          <div className="form-group">
            <label htmlFor="vacina">
              Tipo de Vacina <span className="required">*</span>
            </label>
            <select
              id="vacina"
              name="vacina"
              value={formData.vacina}
              onChange={handleInputChange}
              style={errors.vacina ? { borderColor: '#dc3545' } : {}}
            >
              <option value="">-- Selecionar --</option>
              {vacinas.map((vacina) => (
                <option key={vacina} value={vacina}>
                  {vacina}
                </option>
              ))}
            </select>
            {errors.vacina && <span className="form-error">{errors.vacina}</span>}
          </div>

          <div className="form-group">
            <label htmlFor="dose">
              Dose <span className="required">*</span>
            </label>
            <select
              id="dose"
              name="dose"
              value={formData.dose}
              onChange={handleInputChange}
              style={errors.dose ? { borderColor: '#dc3545' } : {}}
            >
              <option value="">-- Selecionar --</option>
              {doses.map((dose) => (
                <option key={dose} value={dose}>
                  {dose}
                </option>
              ))}
            </select>
            {errors.dose && <span className="form-error">{errors.dose}</span>}
          </div>
        </div>

        <div className="form-row">
          <div className="form-group">
            <label htmlFor="quantidadeAplicada">
              Quantidade Aplicada <span className="required">*</span>
            </label>
            <input
              id="quantidadeAplicada"
              type="number"
              name="quantidadeAplicada"
              placeholder="Ex: 100"
              value={formData.quantidadeAplicada}
              onChange={handleInputChange}
              min="1"
              style={errors.quantidadeAplicada ? { borderColor: '#dc3545' } : {}}
            />
            {errors.quantidadeAplicada && (
              <span className="form-error">{errors.quantidadeAplicada}</span>
            )}
          </div>

          <div className="form-group">
            <label htmlFor="dataRegistro">
              Data do Registro <span className="required">*</span>
            </label>
            <input
              id="dataRegistro"
              type="date"
              name="dataRegistro"
              value={formData.dataRegistro}
              onChange={handleInputChange}
              style={errors.dataRegistro ? { borderColor: '#dc3545' } : {}}
            />
            {errors.dataRegistro && <span className="form-error">{errors.dataRegistro}</span>}
          </div>
        </div>
      </div>

      <div className="form-actions">
        <button type="button" className="btn-cancel" onClick={onCancel} disabled={loading}>
          Cancelar
        </button>
        <button type="submit" className="btn-submit" disabled={loading}>
          {loading ? 'Salvando...' : initialData ? '💾 Atualizar' : '➕ Criar Registro'}
        </button>
      </div>
    </form>
  );
};

export default VaccinationForm;
