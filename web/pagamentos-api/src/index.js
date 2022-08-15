const express = require('express');

const app = express();

app.use(express.json());

app.get('/', (req, res) => {
  res.json({ message: 'Hello World' });
});

app.post('/pagamento/librarysystem', (req, res) => {

  res.json({
    status: 'success',
    message: `Payment Successful`
  })
});

app.listen(3333);