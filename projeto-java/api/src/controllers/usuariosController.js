const pool = require('../config/db'); 

exports.verificarCpf = async (req, res) => {
  const { cpf } = req.params;

  if (!cpf) {
    return res.status(400).json({ error: "CPF is required" });
  }

  try {
    const result = await pool.query('SELECT * FROM usuarios_imobiliaria WHERE cpf = $1', [cpf]);

    if (result.rows.length > 0) {
      return res.status(200).json({ exists: true, message: 'CPF encontrado.' });
    } else {
      return res.status(404).json({ exists: false, message: 'CPF não encontrado.' });
    }
  } catch (error) {
    console.error('Erro ao verificar o CPF:', error);
    return res.status(500).json({ error: 'Erro interno do servidor.' });
  }
};


