import { resolve } from "path"
import { minifyHtml, injectHtml } from "vite-plugin-html"

const scalaVersion = "3.2.0"

// https://vitejs.dev/config/
export default ({ mode }) => {
  const mainJS = `/target/scala-${scalaVersion}/demo-${
    mode === "production" ? "opt" : "fastopt"
  }/main.js`
  console.log("mainJS", mainJS)
  const script = `<script type="module" src="${mainJS}"></script>`

  return {
    publicDir: "./public",
    plugins: [
      ...(process.env.NODE_ENV === "production" ? [minifyHtml()] : []),
      injectHtml({
        injectData: {
          script,
        },
      }),
    ],
    base: "/laminar-ui5-demo/"
  }
}
