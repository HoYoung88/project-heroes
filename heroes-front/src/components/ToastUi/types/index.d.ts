import type { GridOptions, ColumnOptions, RowOptions } from 'tui-grid';

export type TuiGridOptions = Omit<GridOptions, 'el' | 'columns'>;
export type TuiColumnOptions = ColumnOptions;
export type TuiRowOptions = Partial<RowOptions>;
