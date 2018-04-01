import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/components/Home'
import Proposal from '@/components/Proposal'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Home',
      component: Home
    },
    {
      path: '/proposal',
      name: 'Proposal',
      component: Proposal
    }
  ]
})
