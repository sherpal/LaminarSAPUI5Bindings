import { minifyHtml, injectHtml } from "vite-plugin-html"
import { scalaMetadata } from "./scala-metadata"

const scalaVersion = scalaMetadata.scalaVersion

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
