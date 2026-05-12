import axios from 'axios';

// URL base da API do backend
const API_BASE_URL = process.env.REACT_APP_API_URL || 'http://localhost:8080/api';

const vacinacaoService = {
  /**
   * Lista todos os registros de vacinação
   */
  listar: async ({ page = 0, size = 5000 } = {}) => {
    try {
      const response = await axios.get(`${API_BASE_URL}/registros-vacinacao`, {
        params: { page, size },
      });
      return response.data;
    } catch (error) {
      console.error('Erro ao listar registros:', error);
      throw error;
    }
  },

  /**
   * Busca registros por tipo de vacina
   */
  buscarPorVacina: async (vacina) => {
    try {
      const response = await axios.get(`${API_BASE_URL}/registros-vacinacao/vacina/${vacina}`);
      return response.data;
    } catch (error) {
      console.error('Erro ao buscar por vacina:', error);
      throw error;
    }
  },

  /**
   * Busca registros por estado
   */
  buscarPorEstado: async (estado) => {
    try {
      const response = await axios.get(`${API_BASE_URL}/registros-vacinacao/estado/${estado}`);
      return response.data;
    } catch (error) {
      console.error('Erro ao buscar por estado:', error);
      throw error;
    }
  },

  /**
   * Busca registros por município
   */
  buscarPorMunicipio: async (municipio) => {
    try {
      const response = await axios.get(`${API_BASE_URL}/registros-vacinacao/municipio/${municipio}`);
      return response.data;
    } catch (error) {
      console.error('Erro ao buscar por município:', error);
      throw error;
    }
  },

  /**
   * Obtém resumo por estado
   */
  obterResumoPorEstado: async () => {
    try {
      const response = await axios.get(`${API_BASE_URL}/registros-vacinacao/resumo/estado`);
      return response.data;
    } catch (error) {
      console.error('Erro ao obter resumo por estado:', error);
      throw error;
    }
  },

  /**
   * Obtém resumo por tipo de vacina
   */
  obterResumoPorVacina: async () => {
    try {
      const response = await axios.get(`${API_BASE_URL}/registros-vacinacao/resumo/vacina`);
      return response.data;
    } catch (error) {
      console.error('Erro ao obter resumo por vacina:', error);
      throw error;
    }
  },

  /**
   * Cria um novo registro de vacinação
   */
  criar: async (novoRegistro) => {
    try {
      const response = await axios.post(`${API_BASE_URL}/registros-vacinacao`, novoRegistro);
      return response.data;
    } catch (error) {
      console.error('Erro ao criar registro:', error);
      throw error;
    }
  },

  /**
   * Atualiza um registro de vacinação
   */
  atualizar: async (id, registroAtualizado) => {
    try {
      const response = await axios.put(`${API_BASE_URL}/registros-vacinacao/${id}`, registroAtualizado);
      return response.data;
    } catch (error) {
      console.error('Erro ao atualizar registro:', error);
      throw error;
    }
  },

  /**
   * Deleta um registro de vacinação
   */
  deletar: async (id) => {
    try {
      await axios.delete(`${API_BASE_URL}/registros-vacinacao/${id}`);
      return true;
    } catch (error) {
      console.error('Erro ao deletar registro:', error);
      throw error;
    }
  },

  /**
   * Obtém um registro por ID
   */
  obterPorId: async (id) => {
    try {
      const response = await axios.get(`${API_BASE_URL}/registros-vacinacao/${id}`);
      return response.data;
    } catch (error) {
      console.error('Erro ao obter registro:', error);
      throw error;
    }
  }
};

export default vacinacaoService;
