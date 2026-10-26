const { app, BrowserWindow, ipcMain, Menu } = require("electron");
const path = require("path");
const fs = require("fs");
let win = null, pending = null;

function fromArgv(argv) {
  const f = argv.slice(1).find(a => /\.(lokesh|msheet)$/i.test(a) && fs.existsSync(a));
  if (f) pending = fs.readFileSync(f).toString("base64");
}

if (!app.requestSingleInstanceLock()) {
  app.quit();
} else {
  app.on("second-instance", (e, argv) => {
    fromArgv(argv);
    if (win) {
      if (win.isMinimized()) win.restore();
      win.focus();
      win.webContents.executeJavaScript("window.lkCheck&&lkCheck()");
    }
  });
  app.whenReady().then(() => {
    fromArgv(process.argv);
    ipcMain.handle("lk-take", () => { const p = pending; pending = null; return p; });
    Menu.setApplicationMenu(null);
    win = new BrowserWindow({
      width: 1200, height: 800, title: "MiniSheet",
      webPreferences: { preload: path.join(__dirname, "preload.js"), contextIsolation: true }
    });
    win.loadFile(path.join(__dirname, "www", "index.html"));
  });
  app.on("window-all-closed", () => app.quit());
}
