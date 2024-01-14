import { scalaMetadata } from "./scala-metadata"
import { createHtmlPlugin } from 'vite-plugin-html'

import { defineConfig } from 'vite'

const scalaVersion = scalaMetadata.scalaVersion

// https://vitejs.dev/config/
export default defineConfig(({ command, mode, ssrBuild }) => {
  const mainJS = `/target/scala-${scalaVersion}/demo-${
    mode === "production" ? "opt" : "fastopt"
  }/main.js`
  console.log("mainJS", mainJS)
  const script = `<script type="module" src="${mainJS}"></script>`

  return {
    publicDir: "./public",
    plugins: createHtmlPlugin({
         minify: process.env.NODE_ENV === 'production',
         inject: {
             data: {
                 script
             }
         }
     }),
    base: "/laminar-ui5-demo/",
    server: {
      open: '/laminar-ui5-demo'
    },
    build: {
      "chunkSizeWarningLimit": 5000
    }
  }
})
