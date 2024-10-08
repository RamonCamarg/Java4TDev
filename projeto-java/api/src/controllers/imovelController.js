const pool = require('../config/db');

exports.listarImoveis = async (req, res) => {
  try {
    const result = await pool.query('SELECT * FROM imoveis WHERE status = $1', ['DISPONIVEL']);
    res.status(200).json(result.rows);
  } catch (error) {
    console.error('Erro ao listar imóveis', error);
    res.status(500).json({ message: 'Erro ao listar imóveis', error });
  }
};

exports.atualizarStatus = async (req, res) => {
  try {
    const id = req.params.id;
    const imovel = await pool.query('SELECT * FROM imoveis WHERE codigo_id = $1', [id]);
    if(imovel == null){
      return res.status(404).json({ message: "Imovel não encontrado" });
    }
    const result = await pool.query('UPDATE imoveis SET status = $1 WHERE codigo_id = $2', ['ALUGADO', id]);
    res.status(200).json(result.fields);
  } catch (error) {
    console.error('Erro ao editar imóvel', error);
    res.status(500).json({ message: 'Erro ao listar imóveis', error });
  }
}

