const pool = require('../config/db');

class Aluguel {
    static async registrarAluguel(data_inicio, data_fim, usuario_id, imovel_id) {
        const query = `
            INSERT INTO alugueis (data_inicio, data_fim, usuario_id, imovel_id)
            VALUES ($1, $2, $3, $4) RETURNING *;
        `;
        const values = [data_inicio, data_fim, usuario_id, imovel_id];

        try {
            const res = await pool.query(query, values);
            return res.rows[0];
        } catch (error) {
            throw new Error(`Erro ao registrar aluguel: ${error.message}`);
        }
    }
}

module.exports = Aluguel;