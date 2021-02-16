// const mongoose = require('mongoose');

// const BugSchema = new mongoose.Schema({
//     id: {
//         type: Number,
//         required: true
//     },
//     title: {
//         type: String,
//         required: true
//     },
//     project: {
//         type: mongoose.Schema.Types.ObjectId,
//         ref: 'Project'
//     },
//     createdBy: {
//         type: mongoose.Schema.Types.ObjectId,
//         ref: 'User'
//     },
//     assignedTo: {
//         type: mongoose.Schema.Types.ObjectId,
//         ref: 'User'
//     },
//     createdOn: {
//         type: Date,
//         default: Date.now
//     },
//     lastUpdated: {
//         type: Date,
//         default: Date.now
//     },
//     priority: {
//         type: String,
//         default: 'public',
//         enum: ['low', 'medium', 'high']
//     },
//     status: {
//         type: String,
//         default: 'public',
//         enum: ['new', 'in_progress', 'testing', 'closed']
//     },
//     description: {
//         type: String,
//         required: true
//     },
//     image: {
//         type: String
//     }
// });

// module.exports = mongoose.model('Bug', BugSchema);
const sequelize = require('../config/db');
const Project = require('./Project');
const User = require('./User');

const Bug = sequelize.define('bug', {
        id: {
        type: Number,
        required: true,
        primaryKey: true,
        autoIncrement: true
    },
    title: {
        type: String,
        required: true
    },
    // project: {
    //     field: 'project_id',
    //     type: Project,
    //     references: {
    //         model: 'projects',
    //         key: 'id'
    //     }
    // },
    // createdBy: {
    //     field: 'created_by',
    //     type: User,
    //     references: {
    //         model: 'user',
    //         key: 'user_id'
    //     }
    // },
    // assignedTo: {
    //     field: 'assigned_to',
    //     type: User,
    //     references: {
    //         model: 'user',
    //         key: 'user_id'
    //     }
    // },
    createdOn: {
        field: 'created_on',
        type: Date,
        default: Date.now
    },
    lastUpdated: {
        field: 'last_updated',
        type: Date,
        default: Date.now
    },
    priority: {
        type: String,
        default: 'public',
        enum: ['low', 'medium', 'high']
    },
    status: {
        type: String,
        default: 'public',
        enum: ['new', 'in_progress', 'testing', 'closed']
    },
    description: {
        type: String,
        required: true
    },
    image: {
        type: String
    }
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

Bug.belongsTo(User, {
    foreignKey: 'created_by',
    as: 'createdBy'
});
Bug.belongsTo(User, {
    foreignKey: 'assigned_to',
    as: 'assignedTo'
});
Bug.belongsTo(Project, {
    foreignKey: 'project_id'
});
// User.hasMany(Bug);
// Project.hasMany(Bug);

module.exports = Bug;