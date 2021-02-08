const mongoose = require('mongoose');

const UserSchema = new mongoose.Schema({
    id: {
        type: Number,
        required: true
    },
    firstName: {
        type: String,
        required: true
    },
    lastName: {
        type: String,
        required: true
    },
    email: {
        type: String,
        required: true
    },
    role: {
        type: String,
        default: 'dev',
        enum: ['dev', 'lead', 'admin']
    },
    projects: {
        type: mongoose.Schema.Types.ObjectId,
        ref: 'Project'
    }
})