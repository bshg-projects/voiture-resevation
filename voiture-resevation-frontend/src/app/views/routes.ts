import {Routes} from '@angular/router';

export const routes: Routes = [
  {
    path: '',
    data: {title: ''},
    children: [
      {
        path: '',
        redirectTo: 'administrateur',
        pathMatch: 'full'
      },
      {
        path: 'administrateur',
        children: [
          {
            path: '',
            loadComponent: () => import('./administrateur/administrateur-list/administrateur-list.component').then(m => m.AdministrateurListComponent),
            data: {title: 'Administrateur'}
          },
          {
            path: 'create',
            loadComponent: () => import('./administrateur/administrateur-create/administrateur-create.component').then(m => m.AdministrateurCreateComponent),
            data: {title: 'Create Administrateur'}
          },
          {
            path: 'update',
            loadComponent: () => import('./administrateur/administrateur-update/administrateur-update.component').then(m => m.AdministrateurUpdateComponent),
            data: {title: 'update Administrateur'}
          },
        ]
      },
      {
        path: 'client',
        children: [
          {
            path: '',
            loadComponent: () => import('./client/client-list/client-list.component').then(m => m.ClientListComponent),
            data: {title: 'Client'}
          },
          {
            path: 'create',
            loadComponent: () => import('./client/client-create/client-create.component').then(m => m.ClientCreateComponent),
            data: {title: 'Create Client'}
          },
          {
            path: 'update',
            loadComponent: () => import('./client/client-update/client-update.component').then(m => m.ClientUpdateComponent),
            data: {title: 'update Client'}
          },
        ]
      },
      {
        path: 'reservation',
        children: [
          {
            path: '',
            loadComponent: () => import('./reservation/reservation-list/reservation-list.component').then(m => m.ReservationListComponent),
            data: {title: 'Reservation'}
          },
          {
            path: 'create',
            loadComponent: () => import('./reservation/reservation-create/reservation-create.component').then(m => m.ReservationCreateComponent),
            data: {title: 'Create Reservation'}
          },
          {
            path: 'update',
            loadComponent: () => import('./reservation/reservation-update/reservation-update.component').then(m => m.ReservationUpdateComponent),
            data: {title: 'update Reservation'}
          },
        ]
      },
      {
        path: 'offre',
        children: [
          {
            path: '',
            loadComponent: () => import('./offre/offre-list/offre-list.component').then(m => m.OffreListComponent),
            data: {title: 'Offre'}
          },
          {
            path: 'create',
            loadComponent: () => import('./offre/offre-create/offre-create.component').then(m => m.OffreCreateComponent),
            data: {title: 'Create Offre'}
          },
          {
            path: 'update',
            loadComponent: () => import('./offre/offre-update/offre-update.component').then(m => m.OffreUpdateComponent),
            data: {title: 'update Offre'}
          },
        ]
      },
      {
        path: 'voiture',
        children: [
          {
            path: '',
            loadComponent: () => import('./voiture/voiture-list/voiture-list.component').then(m => m.VoitureListComponent),
            data: {title: 'Voiture'}
          },
          {
            path: 'create',
            loadComponent: () => import('./voiture/voiture-create/voiture-create.component').then(m => m.VoitureCreateComponent),
            data: {title: 'Create Voiture'}
          },
          {
            path: 'update',
            loadComponent: () => import('./voiture/voiture-update/voiture-update.component').then(m => m.VoitureUpdateComponent),
            data: {title: 'update Voiture'}
          },
        ]
      },
    ]
  }
];

