const { contextBridge, ipcRenderer } = require("electron");
contextBridge.exposeInMainWorld("LK", { take: () => ipcRenderer.invoke("lk-take") });
