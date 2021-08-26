import { h } from './preact.module.js';
import htm from './htm.module.js';

export * from './preact.module.js';
export const html = htm.bind(h);