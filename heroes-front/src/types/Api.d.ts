interface ApiResponse {
  code: string;
  message: string;
  errorCode?: string;
  errorMessage?: string;
  timestamp: string;
}

type Page = { size: number; number: number; totalElements: number; totalPages: number };

declare namespace Api {
  namespace Meta {
    interface GetResponse<T> extends ApiResponse {
      data: T;
    }
  }

  namespace Character {
    interface GetResponse<T> extends ApiResponse {
      data: T;
    }
  }

  namespace Ranking {
    interface GetResponse<T> extends ApiResponse {
      data: { content: T; page: Page };
    }
  }
}
