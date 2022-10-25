# Release

The project uses [sbt-ci-release](https://github.com/sbt/sbt-ci-release) to automate deployment to Sonatype.

In order to publish, do the following:

- Merge master into publish
- Go to publish
- create a tag with the new version (e.g., `git tag -a v0.4.5 -m "v0.4.5"`)
- push the tag with `git push origin v0.4.5`

Tags must follow along the version of the SAP UI5 library, except for the correction that will be incremented when we fix things within the same UI5 version.

## Demo website deployment

In order to re-deploy the demo website, follow these steps:

```bash
# Go to demo repo
cd demo
# Build the demo website
npm run build
# Clone the demo website repo
git clone https://github.com/sherpal/laminar-ui5-demo
# Remove the assets folder to not keep old stuff
rm -r laminar-ui5-demo/*
# Copy the whole dist folder to the laminar-ui5-demo repo
cp -r dist/* laminar-ui5-demo/.
# Go to the repo and commit, push the changes
cd laminar-ui5-demo
git add .
git commit -m "New version"
git push
```
