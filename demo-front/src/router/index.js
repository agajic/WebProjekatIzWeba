import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import EmployeesView from '../views/EmployeesView.vue'
import EmployeeView from '../views/EmployeeView.vue'
import AddEmployeeView from '../views/AddEmployeeView.vue'
import LoginView from '../views/LoginView.vue'
import RegisterView from '../views/RegisterView.vue'
import UserInfo from '../views/UserInfo.vue'
import ChangeUserInfo from '../views/ChangeUserInfo.vue'
import AdminView from '../views/AdminView.vue'
import AddMenadzerView from '../views/AddMenadzerView.vue'
import AddDostavljacView from '../views/AddDostavljacView.vue'
import AddRestoranView from '../views/AddRestoranView.vue'
import AddDodeliMenadzeraView from '../views/AddDodeliMenadzeraView.vue'
import RestoraniView from '../views/RestoraniView.vue'
import RestoraniDetaljiView from '../views/RestoraniDetaljiView.vue'
import DostavljacView from '../views/DostavljacView.vue'
import MenadzerView from '../views/MenadzerView.vue'
import "bootstrap"
import "bootstrap/dist/css/bootstrap.min.css"

const routes = [
  {
    path: '/home',
    name: 'home',
    component: HomeView
  },
  {
    path: '/employees',
    name: 'employees',
    component: EmployeesView
  },
  {
    path: '/employee',
    name: 'employee',
    component: EmployeeView
  },
  {
    path: '/add-employee',
    name: 'add-employee',
    component: AddEmployeeView
  },
  {
    path: '/loginForm',
    name: 'loginForm',
    component: LoginView
  },
  {
    path: '/registerForm',
    name: 'registerForm',
    component: RegisterView
  },
  {
    path: '/userInfo',
    name: 'userInfo',
    component: UserInfo
  },
  {
    path: '/changeUserInfo',
    name: 'changeUserInfo',
    component: ChangeUserInfo
  },
  {
    path: '/admin',
    name: 'admin',
    component: AdminView
  },
  {
    path: '/admin/addMenadzer',
    name: 'addMenadzer',
    component: AddMenadzerView
  },
  {
    path: '/admin/addDostavljac',
    name: 'addDostavljac',
    component: AddDostavljacView
  },
  {
    path: '/admin/addRestoran',
    name: 'addRestoran',
    component: AddRestoranView
  },
  {
    path: '/admin/dodeliMenadzera',
    name: 'dodeliMenadzera',
    component: AddDodeliMenadzeraView
  },
  {
    path: '/restorani',
    name: 'restorani',
    component: RestoraniView
  },
  {
    path: '/restorani/detalji',
    name: 'restoraniDetalji',
    component: RestoraniDetaljiView
  },
  {
    path: '/dostavljac',
    name: 'dostavljac',
    component: DostavljacView
  },
  {
    path: '/menadzer',
    name: 'menadzer',
    component: MenadzerView
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
