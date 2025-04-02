declare module 'tui-pagination' {
  export type PaginationEventName = 'afterMove' | 'beforeMove';

  export default class Pagination {
    constructor(element: string | HTMLElement, options?: object);

    getCurrentPage(): number;

    movePageTo(targetPage: number): void;

    reset(totalItems?: number): void;

    setItemsPerPage(itemCount: number): void;

    setTotalItems(itemCount: number): void;

    on(eventType: PaginationEventName, callback: (evt: { page: number }) => void): void;

    off(eventType: PaginationEventName): void;
  }
}
