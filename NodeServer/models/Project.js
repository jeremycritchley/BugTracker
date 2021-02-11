// const mongoose = require('mongoose');

// const ProjectSchema = new mongoose.Schema({
//     id: {
//         type: Number,
//         required: true
//     },
//     name: {
//         type: String,
//         required: true
//     }
// })

// module.exports = mongoose.model('Project', ProjectSchema);
const sequelize = require('../config/db');

const Project = sequelize.define('project', {
    id: {
        type: Number,
        required: true,
        primaryKey: true
    }, 
    name: {
        type: String,
        required: true
    }
}, 
{
    // disable the modification of table names; By default, sequelize will automatically
    // transform all passed model names (first parameter of define) into plural.
    // if you don't want that, set the following
    freezeTableName: true,
    schema: 'bug_tracker',
    timestamps: false,
});

module.exports = Project;