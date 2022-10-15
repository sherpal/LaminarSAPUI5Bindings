# Release


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
