
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class LocalStorageService {

  constructor() {}

  setItem(key: string, value: any): void {
    localStorage.setItem(key, JSON.stringify(value));
  }

  getItem<T>(key: string): T | null {
    const item = localStorage.getItem(key);
    if(item != undefined) {
      return JSON.parse(item);
    }
    return null;
  }

  removeItem(key: string): void {
    localStorage.removeItem(key);
  }

  clear(): void {
    localStorage.clear();
  }
}