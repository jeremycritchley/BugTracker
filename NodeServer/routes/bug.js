const express = require('express');
const sequelize = require('../config/db');
const router = express.Router();
const pool = require('../config/db');
const Bug = require('../models/Bug');

// TODO
router.post('/bugs', async (req,res) => {
    try {
       let bug =  await Bug.create(req.body);
       if (bug) {
        res.status(201).json(bug);
       } else {
        res.status(400).send();
       }   
    } catch (error) {
        console.log(error);
        res.status(400).send();
    }
});

// TODO
router.get('/bugs/all/:id', async (req,res) => {
   // res.status(200).json({msg: `GET /bugs/all/${req.params.id}`});
   try {
    let bug = await Bug.findOne({
        where: {
          createdBy: req.params.id
        }
      });
    if (bug) {
        res.json(bug);
    } else {
        res.status(401);
    }
    } catch (error) {
        res.status(500);
    }
});

// TODO
router.get('/bugs/created/:id', async (req,res) => {
    try {
        let bug = await Bug.findOne({
            where: {
              createdBy: req.params.id
            }
          });
        if (bug) {
            res.json(bug);
        } else {
            res.status(404).send();
        }
        } catch (error) {
            res.status(500).send();
        }
});

// TODO
router.get('/bugs/assigned/:id', async (req,res) => {
    try {
        let bug = await Bug.findOne({
            where: {
              assignedTo: req.params.id
            }
          });
        if (bug) {
            res.json(bug);
        } else {
            res.status(404).send();
        }
        } catch (error) {
            res.status(500).send();
        }
});

// TODO
router.get('/bugs/:id', (req,res) => {
    try {
        Bug.findByPk(req.params.id).then((bug) => {
            if (bug) {
                res.json(bug);
            } else {
                res.status(404).send();
            }
        });
        
    } catch (error) {
        res.status(500).send();
    }
});

// TODO
router.get('/bugs', async (req,res) => {
    console.log('IN GET /bugs');
    // try {
    //     const bugs = await pool.query('SELECT * FROM bug_tracker.bug');
    //     res.json(bugs.rows);
    // } catch (error) {
    //     console.log(error);
    //     res.status(500);
    // }
    try {
        const bugs = await Bug.findAll();
        res.json(bugs);
    } catch (error) {
        console.log(error);
        res.status(500);
    }
});

// TODO
router.put('/bugs', (req,res) => {

});

// TODO
router.get('/projects/:id/bugs', (req,res) => {
    res.status(200).json({msg: 'GET /projects/:id/bugs'});
});

module.exports = router;