const express = require('express');
const router = express.Router();
const AluguelController = require('../controllers/aluguelController');

router.post('/aluguel', AluguelController.registrar);

module.exports = router;