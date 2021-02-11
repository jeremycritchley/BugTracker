const express = require('express');
const router = express.Router();
const pool = require('../config/db');

const User = require('../models/User');

// TODO
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

// TODO
router.get('/users', async (req,res) => {
    const users = await User.findAll();
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
    
    res.json(users);
});

// TODO
router.get('/users/:id', (req,res) => {

});

// TODO
router.put('/users/:id', (req,res) => {

});

// TODO
router.get('/projects/:id/users', (req,res) => {

});

module.exports = router;

