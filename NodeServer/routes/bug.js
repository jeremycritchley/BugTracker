const express = require('express');
const { Sequelize } = require('sequelize');
//const { Sequelize } = require('sequelize/types');
const sequelize = require('../config/db');
const Op = Sequelize.Op;
const router = express.Router();
const pool = require('../config/db');
const Bug = require('../models/Bug');
const Project = require('../models/Project');
const User = require('../models/User');

// POST a new Bug
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

// GET all bugs associated with a user ID
router.get('/bugs/all/:id', async (req,res) => {
   // res.status(200).json({msg: `GET /bugs/all/${req.params.id}`});
   try {
    let bugs = await Bug.findAll({
        where: {
          [Op.or]: [{'created_by': req.params.id}, {'assigned_to': req.params.id}]
        },
        include: [
            {
                model: Project
            },
            {
                model: User,
                as: 'createdBy'
            },
            {
                model: User,
                as: 'assignedTo'
            } 
        ],
        attributes: { exclude: ['project_id', 'assigned_to', 'created_by'] }
      });
    if (bugs) {
        res.json(bugs);
    } else {
        res.status(401).send();
    }
    } catch (error) {
        console.log(error);
        res.status(500).send();
    }
});

// GET all bugs created by a User given User ID
router.get('/bugs/created/:id', async (req,res) => {
    try {
        let bugs = await Bug.findAll({
            where: {
              'created_by': req.params.id
            },
            include: [
                {
                    model: Project
                },
                {
                    model: User,
                    as: 'createdBy'
                },
                {
                    model: User,
                    as: 'assignedTo'
                } 
            ],
            attributes: { exclude: ['project_id', 'assigned_to', 'created_by'] }
          });
        if (bugs) {
            res.json(bugs);
        } else {
            res.status(404).send();
        }
        } catch (error) {
            res.status(500).send();
        }
});

// GET all bugs assigned to a User given User ID
router.get('/bugs/assigned/:id', async (req,res) => {
    try {
        let bugs = await Bug.findAll({
            where: {
              'assigned_to': req.params.id
            },
            include: [
                {
                    model: Project
                },
                {
                    model: User,
                    as: 'createdBy'
                },
                {
                    model: User,
                    as: 'assignedTo'
                } 
            ],
            attributes: { exclude: ['project_id', 'assigned_to', 'created_by'] }
          });
        if (bugs) {
            res.json(bugs);
        } else {
            res.status(404).send();
        }
        } catch (error) {
            console.log(error);
            res.status(500).send();
        }
});

// GET Bug with given ID
router.get('/bugs/:id', (req,res) => {
    try {
        Bug.findByPk(req.params.id, {
            include: [
                {
                    model: Project
                },
                {
                    model: User,
                    as: 'createdBy'
                },
                {
                    model: User,
                    as: 'assignedTo'
                } 
            ],
            attributes: { exclude: ['project_id', 'assigned_to', 'created_by'] }
        }).then((bug) => {
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

// GET all bugs
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
        const bugs = await Bug.findAll({
            include: [
                {
                    model: Project
                },
                {
                    model: User,
                    as: 'createdBy'
                },
                {
                    model: User,
                    as: 'assignedTo'
                } 
            ],
            attributes: { exclude: ['project_id', 'assigned_to', 'created_by'] }
        });
        res.json(bugs);
    } catch (error) {
        console.log(error);
        res.status(500);
    }
});

// PUT Bug
router.put('/bugs', (req,res) => {

});

// GET all bugs per Project given Project ID
router.get('/projects/:id/bugs', async (req,res) => {
    try {
        let bugs = await Bug.findAll({
            where: {
              'project_id': req.params.id
            },
            include: [
                {
                    model: Project
                },
                {
                    model: User,
                    as: 'createdBy'
                },
                {
                    model: User,
                    as: 'assignedTo'
                } 
            ],
            attributes: { exclude: ['project_id', 'assigned_to', 'created_by'] }
          });
        if (bugs) {
            res.json(bugs);
        } else {
            res.status(404).send();
        }
        } catch (error) {
            console.log(error);
            res.status(500).send();
        }
});

module.exports = router;