import Vue from 'vue'
import Router from 'vue-router'
import HermitageSignIn from '@/components/HermitageSignIn'

Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'hermitage_sign_in',
      component: HermitageSignIn
    }
  ]
})
