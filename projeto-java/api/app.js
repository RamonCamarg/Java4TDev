const express = require("express");
const cors = require("cors");
const imoveisRoutes = require("./src/routes/imoveisRoutes");
const usuariosRoutes = require("./src/routes/usuariosRoutes");
const aluguelRoutes = require('./src/routes/aluguelRoutes');
require("dotenv").config();
require("./src/config/db");


const app = express();

app.use(cors());
app.use(express.json());

app.use("/imoveis", imoveisRoutes);
app.use("/usuarios", usuariosRoutes);
app.use("/alugueis", aluguelRoutes);

module.exports = app;