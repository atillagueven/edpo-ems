import { createRouter, createWebHistory } from 'vue-router'
import Monitor from "@/views/Monitor.vue";


const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  component: () => import('@/layouts/default/Default.vue'),
  routes: [
    {
      path: '/monitor',
      name: 'monitor',
      component: Monitor
    },
    {
      path: '/',
      name: 'Login',
      // route level code-splitting
      // this generates a separate chunk (about.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import(/* webpackChunkName: "login" */ '@/views/Login.vue'),
    },
  ]
})

export default router
