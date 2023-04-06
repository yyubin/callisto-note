const express = require('express');
const consul = require('consul');
const axios = require('axios');
const app = express();

const consulClient = new consul({
    host: '127.0.0.1',
    port: 8500,
});

app.get('/example', function (req, res) {
    res.send('Hello node.js');
});

app.get('/to-spring-example', function (req, res) {
    axios.get('http://localhost:9000/hello')
    .then(response => {
        res.send(response.data);
    })
    .catch(error => {
        console.log(error);
        res.send('Error : ' + error.message);
    });
});

app.get('/to-spring-to-go', function (req, res) {
    axios.get('http://localhost:9000/example')
    .then(response => {
        res.send(response.data);
    })
    .catch(error => {
        console.log(error);
        res.send('Error : ' + error.message);
    });
});

// JSON 예제 데이터
const jsonData = {
  name: 'John Doe',
  age: 30,
  address: '123 Main St, Anytown USA',
};

// JSON 데이터 반환 API
app.get('/api', (req, res) => {
  res.json(jsonData);
});


const server = app.listen(3001, () => {
    console.log('Server started on port 3001');

    const serviceId = 'nodejs-example-server';
    const serviceName = 'nodejs-example-server';
    const serverPort = 3001;

    consulClient.agent.service.register({
        id: serviceId,
        name: serviceName,
        address: '127.0.0.1',
        port: serverPort,
    }, (err) => {
        if (err) {
        console.error('Failed to register service to Consul', err);
        } else {
        console.log(`Service ${serviceName} registered to Consul`);
        }
    });
});