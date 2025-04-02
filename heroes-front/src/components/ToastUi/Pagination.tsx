import Pagination from 'tui-pagination';

type PaginationProps = {
  totalItems: number;
  itemsPerPage: number;
};

type PaginationEvent = {
  pageMove: (page: number) => void;
};

const assignDefaultOptions = () => {
  return {
    usageStatistics: false,
    totalItems: 0,
    itemsPerPage: 0,
    visiblePages: 5,
    page: 1,
    centerAlign: true,
    template: {
      page: '<li class="flex h-9 shrink-0 cursor-pointer items-center justify-center rounded-md px-3 text-base font-semibold text-slate-900 hover:bg-gray-50">{{page}}</li>',
      currentPage:
        '<li class="flex h-9 shrink-0 cursor-pointer items-center justify-center rounded-md bg-blue-700 px-3 text-base font-semibold text-white">{{page}}</li>',
      moveButton:
        '<li class="flex h-9 w-9 shrink-0 cursor-pointer items-center justify-center rounded-md hover:bg-gray-50">' +
        '<span class="tui-ico-{{type}} text-base">{{type}}</span>' +
        '</li>',
      disabledMoveButton:
        '<li class="flex h-9 w-9 shrink-0 cursor-pointer items-center justify-center rounded-md hover:bg-gray-50">' +
        '<span class="tui-ico-{{type}} text-base">{{type}}</span>' +
        '</li>',
      moreButton: '<li class="hidden"></li>',
    },
  };
};

const createPagination = () => new Pagination('pagination', assignDefaultOptions());

export default defineComponent<PaginationProps, PaginationEvent>(
  (props, { emit }) => {
    const instance = ref<Pagination | null>(null);

    watchEffect(() => {
      if (instance.value) {
        instance.value.setTotalItems(props.totalItems);
        if (props.itemsPerPage) {
          instance.value.setItemsPerPage(props.itemsPerPage);
        }
        instance.value.reset();
      }
    });

    onMounted(async () => {
      await nextTick();
      instance.value = createPagination();
      instance.value.setItemsPerPage(props.itemsPerPage);
      instance.value.on('beforeMove', ({ page }) => {
        emit('pageMove', page);
      });
    });

    return () =>
      h('ul', { id: 'pagination', class: 'flex justify-center space-x-2 tui-pagination' });
  },
  {
    props: {
      totalItems: {
        type: Number,
        default: 0,
      },
      itemsPerPage: {
        type: Number,
        default: 20,
      },
    },
    emits: ['pageMove'],
    inheritAttrs: false,
  },
);
