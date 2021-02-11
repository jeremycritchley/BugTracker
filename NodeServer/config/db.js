// const mongoose = require('mongoose');

// const connectDB = async () => {
//     try {
//         const conn = await mongoose.connect(process.env.MONGO_URI, {
//             useNewUrlParser: true,
//             useUnifiedTopology: true,
//             useFindAndModify: false
//         });
//         console.log(`MongoDB Connected: ${conn.connection.host}`);
//     } catch (err) {
//         console.error(err);
//         process.exit(1);
//     }
// }
// const Pool = require('pg').Pool;

// const pool = new Pool({
//     user: 'postgres',
//     password: 'ta4k3it!',
//     database: 'postgres',
//     schema: 'bug_tracker',
//     host: 'localhost',
//     port: 5432,
// })

// module.exports = pool;
const Sequelize = require('sequelize');

const sequelize = new Sequelize(`postgres://postgres:ta4k3it!@localhost:5432/postgres?currentSchema=bug_tracker`);

module.exports = sequelize;