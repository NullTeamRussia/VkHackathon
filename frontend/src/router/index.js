import Vue from 'vue'
import Router from 'vue-router'
import HermitageSignIn from '@/components/HermitageSignIn'
import HermitagePanelMain from '@/components/HermitagePanelMain'

Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'hermitage_sign_in',
      component: HermitageSignIn
    },
    {
      path: '/panel',
      name: 'hermitage_panel_main',
      component: HermitagePanelMain
    }
  ]
})
