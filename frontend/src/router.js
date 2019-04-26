// noinspection JSAnnotator

import Vue from 'vue'
import Router from 'vue-router'

//import Hello from '@/components/Hello'
//import Service from '@/components/Service'
//import Bootstrap from '@/components/Bootstrap'
//import User from '@/components/User'


import memberDetails from './components/memberDetails.vue';
import groupMembers from './components/GroupMembers.vue'
import addMember from './components/addMember.vue'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'GroupMembers',
      component: groupMembers
    },
    {
      path: '/view/:id',
      name: 'memberDetails',
      component: memberDetails
    },
    {
      path: '/addMember',
      name: 'addMember',
      component: addMember
    }
  ]
})
