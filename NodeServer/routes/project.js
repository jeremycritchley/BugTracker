const express = require('express');
const router = express.Router();
const pool = require('../config/db');

const Project = require('../models/Project');

// TODO
router.get('/projects/:id', (req,res) => {

});

// TODO
router.post('/projects', (req,res) => {

});

// TODO
router.get('/projects', async (req,res) => {
    console.log('IN GET /projects');
    // try {
    //     const projects = await pool.query('SELECT * FROM bug_tracker.project');
    //     res.json(projects.rows);
    // } catch (error) {
    //     console.log(error);
    //     res.status(500);
    // }
    try {
        const projects = await Project.findAll();
        res.json(projects);
    } catch (error) {
        res.status(500);
    }

});

// TODO
router.get('/projects/:id/users', (req,res) => {

});

// TODO
router.get('/users/:id/projects', (req,res) => {

});

module.exports = router;