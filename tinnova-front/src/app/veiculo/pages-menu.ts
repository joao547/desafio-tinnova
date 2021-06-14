import { NbMenuItem } from '@nebular/theme';

export const MENU_ITEMS: NbMenuItem[] = [
  {
    title: 'Veiculo',
    icon: 'car-outline',
    link: '/veiculo',
    home: true,
    children: [
      {
        title: 'Listar',
        icon: 'list-outline',
        link: '/veiculo/listar',
      },
      {
        title: 'Adicionar',
        icon: 'plus-outline',
        link: '/veiculo/adicionar',
      },
    ],
  },
];
