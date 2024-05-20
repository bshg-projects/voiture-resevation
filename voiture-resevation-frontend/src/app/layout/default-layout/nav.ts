import {INavData} from '@coreui/angular';

export const navItems: INavData[] = [
  {
    name: 'Dashboard',
    url: '/dashboard',
    iconComponent: {name: 'cil-speedometer'},
    badge: {
      color: 'info',
      text: 'NEW',
    },
  },
  {
    name: ' Administrateur',
    url: '/administrateur',
    icon: 'nav-icon-bullet',
  },
  {
    name: ' Client',
    url: '/client',
    icon: 'nav-icon-bullet',
  },
  {
    name: ' Reservation',
    url: '/reservation',
    icon: 'nav-icon-bullet',
  },
  {
    name: ' Offre',
    url: '/offre',
    icon: 'nav-icon-bullet',
  },
  {
    name: ' Voiture',
    url: '/voiture',
    icon: 'nav-icon-bullet',
  },
];
