const express = require('express');
const dotenv = require('dotenv');
const morgan = require('morgan');
const pool = require('./config/db');

// Load config
dotenv.config({path: './config/config.env'});

//connectDB();
// pool.query('SELECT * FROM pg_catalog.pg_tables', (err, result) => {
//     console.log(result);
//     console.log(err);
// })

const app = express();

if (process.env.NODE_ENV === 'development') {
    app.use(morgan('dev'));
}

// Body parser
app.use(express.urlencoded({extended: false}));
app.use(express.json());

// Routes
app.use('/', require('./routes/bug'));
app.use('/', require('./routes/project'));
app.use('/', require('./routes/user'));

const PORT = process.env.PORT || 5000;

app.listen(PORT, () => console.log(`Server started on port ${PORT} in ${process.env.NODE_ENV} mode`));