const express = require("express");
const router = express.Router();
const { verificarCpf } = require('../controllers/usuariosController'); // Ajuste o caminho do seu controller

// Rota para verificar se o CPF existe
router.get('/verificar-cpf/:cpf', verificarCpf);

module.exports = router;