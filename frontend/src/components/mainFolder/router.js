// noinspection JSAnnotator
/*import Vue from 'vue'
import Router from 'vue-router'
import Hello from '@/components/Hello'
import Service from '@/components/Service'
import Bootstrap from '@/components/Bootstrap'
import User from '@/components/User'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Hello',
      component: Hello
    },
    {
      path: '/callservice',
      name: 'Service',
      component: Service
    },
    {
      path: '/bootstrap',
      name: 'Bootstrap',
      component: Bootstrap
    },
    {
      path: '/user',
      name: 'User',
      component: User
    }
  ]
})
*/

import memberDetails from './components/memberDetails.vue';
import groupMembers from './components/GroupMembers.vue'
import addMember from './components/addMember.vue'


export default[
    {path:"/",component:groupMembers},
    {path:"/view/:id",component:memberDetails},
    {path:"/addMember",component:addMember}
]