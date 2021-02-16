const express = require('express');
const router = express.Router();
const pool = require('../config/db');

const Project = require('../models/Project');

// GET Project by given ID
router.get('/projects/:id', async (req,res) => {
    try {
        const project = await Project.findByPk(req.params.id);
        if (project) {
            res.json(project);
        } else {
            res.status(404).send();
        }
    } catch (error) {
        res.status(500).send();
    }
});

// TODO
router.post('/projects', (req,res) => {

});

// GET all projects
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
        res.status(500).send();
    }

});

// TODO
// router.get('/projects/:id/users', (req,res) => {

// });

// TODO
router.get('/users/:id/projects', (req,res) => {

});

module.exports = router;