const mongoose = require('mongoose');
import Project from('Project');

const BugSchema = new mongoose.Schema({
    id: {
        type: Number,
        required: true
    },
    title: {
        type: String,
        required: true
    },
    project: {
        type: mongoose.Schema.Types.ObjectId,
        ref: 'Project'
    },
    createdBy: {
        type: mongoose.Schema.Types.ObjectId,
        ref: 'User'
    },
    assignedTo: {
        type: mongoose.Schema.Types.ObjectId,
        ref: 'User'
    },
    createdOn: {
        type: Date,
        default: Date.now
    },
    lastUpdated: {
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
});

module.exports = mongoose.model('User', UserSchema);