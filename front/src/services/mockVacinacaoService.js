// Mock Service - Use isso durante o desenvolvimento antes do backend estar pronto

const mockData = [
  {
    id: 1,
    municipio: 'São Paulo',
    estado: 'SP',
    vacina: 'COVID-19',
    dose: '1ª dose',
    quantidadeAplicada: 1500,
    dataRegistro: '2026-05-01',
  },
  {
    id: 2,
    municipio: 'Rio de Janeiro',
    estado: 'RJ',
    vacina: 'Gripe',
    dose: '1ª dose',
    quantidadeAplicada: 1200,
    dataRegistro: '2026-05-02',
  },
  {
    id: 3,
    municipio: 'Belo Horizonte',
    estado: 'MG',
    vacina: 'BCG',
    dose: 'Dose única',
    quantidadeAplicada: 800,
    dataRegistro: '2026-05-03',
  },
  {
    id: 4,
    municipio: 'Brasília',
    estado: 'DF',
    vacina: 'Tétano',
    dose: '2ª dose',
    quantidadeAplicada: 600,
    dataRegistro: '2026-05-04',
  },
  {
    id: 5,
    municipio: 'Salvador',
    estado: 'BA',
    vacina: 'Hepatite B',
    dose: 'Reforço',
    quantidadeAplicada: 900,
    dataRegistro: '2026-05-05',
  },
  {
    id: 6,
    municipio: 'Recife',
    estado: 'PE',
    vacina: 'Poliomielite',
    dose: '1ª dose',
    quantidadeAplicada: 750,
    dataRegistro: '2026-05-06',
  },
  {
    id: 7,
    municipio: 'Porto Alegre',
    estado: 'RS',
    vacina: 'Meningite',
    dose: '2ª dose',
    quantidadeAplicada: 1100,
    dataRegistro: '2026-05-07',
  },
  {
    id: 8,
    municipio: 'Fortaleza',
    estado: 'CE',
    vacina: 'Hepatite A',
    dose: 'Dose única',
    quantidadeAplicada: 950,
    dataRegistro: '2026-05-08',
  },
];

const mockVacinacaoService = {
  listar: async () => {
    await new Promise(resolve => setTimeout(resolve, 500)); // Simula delay de rede
    return mockData;
  },

  buscarPorVacina: async (vacina) => {
    await new Promise(resolve => setTimeout(resolve, 500));
    return mockData.filter(r => r.vacina === vacina);
  },

  buscarPorEstado: async (estado) => {
    await new Promise(resolve => setTimeout(resolve, 500));
    return mockData.filter(r => r.estado === estado);
  },

  buscarPorMunicipio: async (municipio) => {
    await new Promise(resolve => setTimeout(resolve, 500));
    return mockData.filter(r =>
      r.municipio.toLowerCase().includes(municipio.toLowerCase())
    );
  },

  obterResumoPorEstado: async () => {
    await new Promise(resolve => setTimeout(resolve, 500));
    const resumo = {};
    mockData.forEach(r => {
      resumo[r.estado] = (resumo[r.estado] || 0) + 1;
    });
    return resumo;
  },

  obterResumoPorVacina: async () => {
    await new Promise(resolve => setTimeout(resolve, 500));
    const resumo = {};
    mockData.forEach(r => {
      resumo[r.vacina] = (resumo[r.vacina] || 0) + r.quantidadeAplicada;
    });
    return resumo;
  },

  criar: async (novoRegistro) => {
    await new Promise(resolve => setTimeout(resolve, 500));
    const novoId = Math.max(...mockData.map(r => r.id), 0) + 1;
    const registroCompleto = {
      ...novoRegistro,
      id: novoId,
    };
    mockData.push(registroCompleto);
    return registroCompleto;
  },

  atualizar: async (id, registroAtualizado) => {
    await new Promise(resolve => setTimeout(resolve, 500));
    const indice = mockData.findIndex(r => r.id === id);
    if (indice !== -1) {
      mockData[indice] = { ...mockData[indice], ...registroAtualizado, id };
      return mockData[indice];
    }
    throw new Error('Registro não encontrado');
  },

  deletar: async (id) => {
    await new Promise(resolve => setTimeout(resolve, 500));
    const indice = mockData.findIndex(r => r.id === id);
    if (indice !== -1) {
      mockData.splice(indice, 1);
      return true;
    }
    throw new Error('Registro não encontrado');
  },

  obterPorId: async (id) => {
    await new Promise(resolve => setTimeout(resolve, 500));
    return mockData.find(r => r.id === id);
  }
};

export default mockVacinacaoService;
