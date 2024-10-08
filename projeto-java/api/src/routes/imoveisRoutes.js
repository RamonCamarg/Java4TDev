const express = require("express");
const router = express.Router();
const { listarImoveis, atualizarStatus } = require("../controllers/imovelController");

// Definindo as rotas e associando aos métodos do controller
router.get("/", listarImoveis); // Listar todos os livros
router.post("/:id", atualizarStatus); // Listar todos os livros

module.exports = router;