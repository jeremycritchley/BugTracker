const express = require('express');
const router = express.Router();
const pool = require('../config/db');
const Project = require('../models/Project');

const User = require('../models/User');

// Create new user
router.post('/users', async (req,res) => {
    try {
        let user =  await User.create(req.body);
        if (user) {
         res.status(201).json(user);
        } else {
         res.status(400).send();
        }   
     } catch (error) {
         console.log(error);
         res.status(400).send();
     }
 });

// GET all users
router.get('/users', async (req,res) => {
    
    // try {
    //     const users = await User.find();
    //     res.status(200).json(users);
    // } catch (error) {
    //     console.log(error);
    //     res.status(500).send();
    // }
    // console.log('IN GET /users');
    // try {
    //     const users = await pool.query('SELECT * FROM bug_tracker."user"');
    //     res.json(users.rows);
    // } catch (error) {
    //     console.log(error);
    //     res.status(500);
    // }
    try {
        const users = await User.findAll();
        res.json(users);
    } catch (error) {
        console.log(error);
        res.status(500).send();
    }
    
});

// TODO
router.get('/users/:id', async (req,res) => {
    try {
        const user = await User.findByPk(req.params.id);
        if (user) {
            res.json(user);
        } else {
            res.status(404).send();
        }
    } catch (error) {
        console.log(error);
        res.status(500).send();
    }
});

// TODO
router.put('/users/:id', (req,res) => {

});

// TODO
router.get('/projects/:id/users', async (req,res) => {
    console.log(`GET  /projects/:${req.params.id}/users`);
    try {
        const user = await User.findAll({
            include: [{
                model: Project
                // through: {
                //     where: {'project_id': req.params.id},
                // }
               }]
        });
        if (user) {
            res.json(user);
        } else {
            res.status(404).send();
        }
    } catch (error) {
        console.log(error);
        res.status(500).send();
    }
});

module.exports = router;

