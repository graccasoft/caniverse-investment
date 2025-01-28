<!DOCTYPE html>
<html lang="en">
<head>
    @template.partials.header(title = "Dashboard")
</head>
<body>
@template.partials.preloader()
<div class="flex h-screen overflow-hidden">
    @template.investor.aside()
    <div class="relative flex flex-1 flex-col overflow-y-auto overflow-x-hidden">
        @template.partials.profile()
        <main>
            <div class="mx-auto max-w-screen-2xl p-4 md:p-6 2xl:p-10">

                content for each page

            </div>
        </main>
    </div>
</div>
@template.partials.footer()
</body>

</html>