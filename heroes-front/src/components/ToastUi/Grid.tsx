import Grid, {
  type ColumnOptions,
  type GridEventName,
  type GridOptions,
  type RowOptions,
} from 'tui-grid';

export type TuiGridOptions = Omit<GridOptions, 'el' | 'columns'>;
export type TuiColumnOptions = ColumnOptions;
export type TuiRowOptions = Partial<RowOptions>;

interface GridProps {
  columns: Partial<TuiColumnOptions[]>;
  data?: TuiRowOptions[];
  options?: Omit<TuiGridOptions, 'el' | 'columns'>;
  height?: string | number;
}

const assignDefaultOptions = (options: TuiGridOptions | undefined): TuiGridOptions => {
  const { usageStatistics, bodyHeight, ...otherOptions } = options || {};

  return {
    usageStatistics: false,
    scrollX: false,
    scrollY: true,
    ...otherOptions,
  };
};

function pascalToCamel(str: string): string {
  return str.charAt(0).toLowerCase() + str.slice(1);
}

export default defineComponent<GridProps>(
  (props, { slots, attrs, emit }) => {
    const templateRef = useTemplateRef<HTMLElement>('grid');
    const instance = ref<Grid | null>(null);
    const gridEventNames: GridEventName[] = Object.entries(attrs)
      .filter(([_, value]) => typeof value === 'function')
      .map(([key, _]) => pascalToCamel(key.replace(/^on/, '')) as GridEventName);

    const initializeGrid = () => {
      if (templateRef.value !== null) {
        instance.value = new Grid({
          el: templateRef.value,
          columns: props.columns as TuiColumnOptions[],
          bodyHeight: Number(props.height),
          ...assignDefaultOptions(props.options),
        });
      }
    };

    const addEventListener = (gridEventNames: GridEventName[]) => {
      gridEventNames.forEach((eventName) => {
        instance.value?.on(eventName, (e) => {
          emit(eventName, e);
        });
      });
    };

    const removeEventListener = (gridEventNames: GridEventName[]) => {
      gridEventNames.forEach((eventName) => {
        instance.value?.off(eventName);
      });
    };

    watchEffect(() => {
      instance.value?.resetData(props.data ? props.data : []);
    });

    onMounted(async () => {
      await nextTick();
      initializeGrid();
      addEventListener(gridEventNames);
      Grid.setLanguage('ko');
      Grid.applyTheme('striped', {
        outline: {
          // border: '1px solid #f4f4f4',
        },
        selection: {
          background: '',
          border: 'transparent',
        },
        frozenBorder: {
          border: 'none',
        },
        cell: {
          normal: {
            // border: '1px solid #f4f4f4',
            showVerticalBorder: true,
            showHorizontalBorder: true,
          },
          header: {
            // border: '1px solid #f4f4f4',
            showVerticalBorder: true,
            showHorizontalBorder: true,
          },
          rowHeader: {
            // border: '1px solid #f4f4f4',
            showVerticalBorder: true,
            showHorizontalBorder: true,
          },
          selectedHeader: {
            background: '',
          },
          selectedRowHeader: {
            background: '',
          },
          focused: {
            border: '0',
          },
          focusedInactive: {
            border: '0',
          },
        },
        row: {
          hover: {
            background: '#e5e5e5',
          },
        },
      });
    });

    onUnmounted(() => {
      removeEventListener(gridEventNames);
      instance.value?.destroy();
    });

    return () =>
      h('div', null, [
        //
        h('div', { id: 'tui-grid', ref: 'grid' }),
        //
        h('div', {}, [slots.default?.()]),
      ]);
  },
  {
    props: {
      columns: {
        type: Array,
        default: () => [],
      },
      data: {
        type: Array,
        default: () => [],
      },
      options: {
        type: Object,
        default: () => {},
      },
      height: {
        type: [Number, String],
        default: 400,
      },
    },
    inheritAttrs: false,
  },
);
