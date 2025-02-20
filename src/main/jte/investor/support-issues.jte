@param java.util.List<io.caniverse.investment.model.entity.SupportIssue> issues
@param io.caniverse.investment.model.entity.Investor investor
<!DOCTYPE html>
<html lang="en">
<head>
    @template.partials.header(title = "Support")
</head>
<body
        x-data="{ page: 'support', 'loaded': true, 'darkMode': true, 'stickyMenu': false, 'sidebarToggle': false, 'scrollTop': false }"
>
@template.partials.preloader()
<div class="flex h-screen overflow-hidden">
    @template.investor.aside()
    <div class="relative flex flex-1 flex-col overflow-y-auto overflow-x-hidden">
        @template.partials.profile(investor = investor)
        <main>
            <div class="mx-auto max-w-screen-2xl p-4 md:p-6 2xl:p-10">

                <h2 class="text-title-md2 font-bold text-black dark:text-white mb-4">Support Issues</h2>
                <div class="rounded-sm border border-stroke bg-white shadow-default dark:border-strokedark dark:bg-boxdark">
                    <div class="px-4 py-6 md:px-6 xl:px-7.5 text-right">
                        <a href="/investor/support/new"
                           class="inline-flex items-center justify-center gap-2.5 rounded-md bg-primary px-10 py-2 text-center font-medium text-white hover:bg-opacity-90 lg:px-8 xl:px-10">
                    Report an Issue
                        </a>
                    </div>

                    <div class="grid grid-cols-2 gap-4 px-4 border-b mb-2">
                        <div class="col-span-1  items-center">
                            <p class="font-medium">Subject</p>
                        </div>
                        <div class="col-span-1  items-center text-right">
                            <p class="font-medium">Status</p>
                        </div>

                    </div>

                    @for(var issue: issues)
                    <div class="grid grid-cols-2 gap-4 px-4">
                        <div class="col-span-1  items-center">
                            <div class="flex flex-col gap-4 sm:flex-row sm:items-center">

                                <p class="text-sm font-medium text-black dark:text-white">
                                    ${issue.getSubject()}<br>
                                    [ <a href="/investor/support/${issue.getId()}">View</a> ]
                                </p>
                            </div>
                        </div>

                        <div class="col-span-1  items-center  text-right">
                            <p class="inline-flex rounded-full bg-success bg-opacity-10 px-3 py-1 text-sm font-medium text-success">
                                ${issue.getStatus()}
                            </p>
                        </div>
                    </div>
                    @endfor


                </div>
            </div>
        </main>
    </div>
</div>
@template.partials.footer()
</body>

</html>