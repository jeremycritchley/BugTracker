// const mongoose = require('mongoose');
// const Project = require('./Project');

// const UserSchema = new mongoose.Schema({
//     id: {
//         type: Number,
//         required: true
//     },
//     firstName: {
//         type: String,
//         required: true
//     },
//     lastName: {
//         type: String,
//         required: true
//     },
//     email: {
//         type: String,
//         required: true
//     },
//     role: {
//         type: String,
//         default: 'DEV',
//         enum: ['DEV', 'LEAD', 'ADMIN']
//     },
//     projects: {
//         type: Project,
//         ref: 'Project'
//     }
// })

// module.exports = mongoose.model('User', UserSchema);
const Project = require('./Project');
const sequelize = require('../config/db');

const User = sequelize.define('user', {
    id: {
        field: 'user_id',
        type: Number,
        required: true,
        primaryKey: true,
        autoIncrement: true
    },
    email: {
        type: String,
        required: true
    },
    firstName: {
        field: 'first_name',
        type: String,
        required: true
    },
    lastName: {
        field: 'last_name',
        type: String,
        required: true
    },
    role: {
        field: 'role_id',
        type: String,
        default: 'DEV',
        enum: ['DEV', 'LEAD', 'ADMIN']
    }
    // },
    // projects: {
    //     type: Project,
    //     ref: 'Project'
    // }
}, 
{
    // disable the modification of table names; By default, sequelize will automatically
    // transform all passed model names (first parameter of define) into plural.
    // if you don't want that, set the following
    freezeTableName: true,
    schema: 'bug_tracker',
    timestamps: false,
    underscored: true,
});

User.belongsToMany(Project, {through: 'user_projects'});
Project.belongsToMany(User, {through: 'user_projects'});

module.exports = User;