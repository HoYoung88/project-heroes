declare module '#app' {
  interface NuxtApp {
    $meta: {
      character: {
        physicalClass: string[];
        magicClass: string[];
        skill: Record<string, string[]>;
      };
      equipment: {
        slotName: Record<string, string>;
        slotOrders: string[];
      };
    };
  }
}

export {};
