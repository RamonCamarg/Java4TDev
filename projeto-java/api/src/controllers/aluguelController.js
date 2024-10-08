const Aluguel = require('../models/aluguel');
require('dotenv').config();


class AluguelController {
    static async registrar(req, res) {
        const { data_inicio, data_fim, usuario_id, imovel_id } = req.body;
        const url = "http://" + process.env.DB_HOST + ":" + process.env.PORT + "/imoveis/" + imovel_id;

        const dataAtual = new Date().toISOString().split('T')[0];

        if (data_inicio < dataAtual) {
            return res.status(400).json({
                message: "A data de início não pode ser anterior à data atual.",
            });
        }

        if (data_fim <= data_inicio) {
            return res.status(400).json({
                message: "A data de fim deve ser posterior à data de início.",
            });
        }

        if (imovel_id == null || usuario_id == null){
            return res.status(400).json({
                message: "Usuário ou imovel não especificado.",
            });
        }

        try {
            
            fetch(url, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json' 
                },
            })
            const novoAluguel = await Aluguel.registrarAluguel(data_inicio, data_fim, usuario_id, imovel_id);
            res.status(201).json({
                message: "Aluguel registrado com sucesso!",
                aluguel: novoAluguel,
            });
        } catch (error) {
            res.status(500).json({
                message: "Erro ao registrar aluguel",
                error: error.message,
            });
        }
    }
}

module.exports = AluguelController;